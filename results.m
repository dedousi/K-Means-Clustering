function results
      close all;
      % for each file that we have created for M={3,5,13}
      fileNames={"Results_for_3_clusters.txt";"Results_for_5_clusters.txt";"Results_for_13_clusters.txt"};
      fileNumber = size(fileNames);
      Ms = zeros(3,1);
      minerror = zeros(3,1);
      errors = zeros(30,1);

     for i=1 : 3

            % get the file name.
            fileName = fileNames{i};

            % get the number of clusters (M).
            M = textread(fileName,'The clusters were M=%d',1,'endofline','\n');
            Ms(i) = M;

            % get the errors:
            errors = textread(fileName,'The error for loop %*d was: %f',20,'headerlines',1,'endofline','\n');

            % get min error value.
            minError = textread(fileName,'The min.error was %f',1,'endofline','\n','headerlines',22);
            minerror(i) = minError;

            % get the cluster representatives and the number of the clusters.
           [clusterMeansX, clusterMeansY]= textread(fileName, 'The means for cluster %*d is: (%f,%f)',M,'endofline','\n','headerlines',25);

            % get how many points each cluster has.
            num = 25 + M + 1;
            clusterSizes = textread(fileName, 'cluster %*d has %d points.',M,'endofline','\n','headerlines',num);

            % get the points.
            num = num + M + 1;
            [clustersX,clustersY] = textread(fileName,'(%f,%f)','endofline','\n','headerlines',num);

            % draw.
            if( M == 3)
                  f1 = figure('Name',"Results for 3 clusters");
                  for k = 1 : clusterSizes(1)
                        scatter(clustersX(k),clustersY(k),'y','+'); hold on;
                  endfor
                  for k = 1 : clusterSizes(2)
                        num = clusterSizes(1)+k;
                        scatter(clustersX(num),clustersY(num),'c','+'); hold on;
                  endfor
                  for k = 1 : clusterSizes(3)
                        num = clusterSizes(1)+clusterSizes(2)+k;
                        scatter(clustersX(num),clustersY(num),'g','+'); hold on;
                  endfor
                  scatter(clusterMeansX,clusterMeansY,'k','*');
                  saveas(f1,"plot_for_3_clusters.jpg");

            elseif(M == 5)
                  f2 = figure('Name',"Results for 5 clusters");
                  for k = 1 : clusterSizes(1)
                        scatter(clustersX(k),clustersY(k),'y','+'); hold on;
                  endfor
                  for k = 1 : clusterSizes(2)
                        num = clusterSizes(1)+k;
                        scatter(clustersX(num),clustersY(num),'c','+'); hold on;
                  endfor
                  for k = 1 : clusterSizes(3)
                        num = clusterSizes(1)+clusterSizes(2)+k;
                        scatter(clustersX(num),clustersY(num),'g','+'); hold on;
                  endfor
                  for k = 1 : clusterSizes(4)
                        num = clusterSizes(1)+clusterSizes(2)+clusterSizes(3)+k;
                        scatter(clustersX(num),clustersY(num),50,[0.7529,0.549,0.2667],'+'); hold on;
                  endfor
                  for k = 1 : clusterSizes(5)
                        num = clusterSizes(1)+clusterSizes(2)+clusterSizes(3)+clusterSizes(4)+k;
                        scatter(clustersX(num),clustersY(num),50,[0.6825,0.5647,0.8549],'+'); hold on;
                  endfor
                  scatter(clusterMeansX,clusterMeansY,'k','*');
                  saveas(f2,"plot_for_5_clusters.jpg");

            elseif(M==13)
                  f6 = figure('Name',"Results for 13 clusters");
                  for k = 1 : clusterSizes(1)
                        scatter(clustersX(k),clustersY(k),'y','+'); hold on;
                  endfor
                  for k = 1 : clusterSizes(2)
                        num = clusterSizes(1)+k;
                        scatter(clustersX(num),clustersY(num),'c','+'); hold on;
                  endfor
                  for k = 1 : clusterSizes(3)
                        num = clusterSizes(1)+clusterSizes(2)+k;
                        scatter(clustersX(num),clustersY(num),'g','+'); hold on;
                  endfor
                  for k = 1 : clusterSizes(4)
                        num = clusterSizes(1)+clusterSizes(2)+clusterSizes(3)+k;
                        scatter(clustersX(num),clustersY(num),50,[0.7529,0.549,0.2667],'+'); hold on;
                  endfor
                  for k = 1 : clusterSizes(5)
                        num = clusterSizes(1)+clusterSizes(2)+clusterSizes(3)+clusterSizes(4)+k;
                        scatter(clustersX(num),clustersY(num),50,[0.6825,0.5647,0.8549],'+'); hold on;
                  endfor
                  for k = 1 : clusterSizes(6)
                        num = clusterSizes(1)+clusterSizes(2)+clusterSizes(3)+clusterSizes(4)+clusterSizes(5)+k;
                        scatter(clustersX(num),clustersY(num),50,[0.8667,0.6196,0.6039],'+'); hold on;
                  endfor
                  for k = 1 : clusterSizes(7)
                        num = clusterSizes(1)+clusterSizes(2)+clusterSizes(3)+clusterSizes(4)+clusterSizes(5)+clusterSizes(6)+k;
                        scatter(clustersX(num),clustersY(num),50,[0.5216,0.5804,0.8392],'+'); hold on;
                  endfor
                  for k = 1 : clusterSizes(8)
                        num = clusterSizes(1)+clusterSizes(2)+clusterSizes(3)+clusterSizes(4)+clusterSizes(5)+clusterSizes(6)+clusterSizes(7)+k;
                        scatter(clustersX(num),clustersY(num),50,[0.9765,0.6471,0.3451],'+'); hold on;
                  endfor
                  for k = 1 : clusterSizes(9)
                        num = clusterSizes(1)+clusterSizes(2)+clusterSizes(3)+clusterSizes(4)+clusterSizes(5)+clusterSizes(6)+clusterSizes(7)+clusterSizes(8)+k;
                        scatter(clustersX(num),clustersY(num),50,[0.349,0.9725,0.9137],'+'); hold on;
                  endfor
                  for k = 1 : clusterSizes(10)
                        num = clusterSizes(1)+clusterSizes(2)+clusterSizes(3)+clusterSizes(4)+clusterSizes(5)+clusterSizes(6)+clusterSizes(7)+clusterSizes(8)+clusterSizes(9)+k;
                        scatter(clustersX(num),clustersY(num),50,[0.9882,0.7255,0.8824],'+'); hold on;
                  endfor
                  for k = 1 : clusterSizes(11)
                        num = clusterSizes(1)+clusterSizes(2)+clusterSizes(3)+clusterSizes(4)+clusterSizes(5)+clusterSizes(6)+clusterSizes(7)+clusterSizes(8)+clusterSizes(9)+clusterSizes(10)+k;
                        scatter(clustersX(num),clustersY(num),50,[0.7373,0.9882,0.7373],'+'); hold on;
                  endfor
                  for k = 1 : clusterSizes(12)
                        num = clusterSizes(1)+clusterSizes(2)+clusterSizes(3)+clusterSizes(4)+clusterSizes(5)+clusterSizes(6)+clusterSizes(7)+clusterSizes(8)+clusterSizes(9)+clusterSizes(10)+clusterSizes(11)+k;
                        scatter(clustersX(num),clustersY(num),50,[0.7608,0.7373,0.9882],'+'); hold on;
                  endfor
                  for k = 1 : clusterSizes(13)
                        num = clusterSizes(1)+clusterSizes(2)+clusterSizes(3)+clusterSizes(4)+clusterSizes(5)+clusterSizes(6)+clusterSizes(7)+clusterSizes(8)+clusterSizes(9)+clusterSizes(10)+clusterSizes(11)+clusterSizes(12)+k;
                        scatter(clustersX(num),clustersY(num),50,[0.9647,0.9686,0.3294],'+'); hold on;
                  endfor
                  scatter(clusterMeansX,clusterMeansY,'k','*');
                  saveas(f6,"plot_for_13_clusters.jpg");
            endif
      endfor
endfunction
